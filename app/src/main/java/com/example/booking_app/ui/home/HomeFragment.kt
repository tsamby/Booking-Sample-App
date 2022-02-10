package com.example.booking_app.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.booking_app.R
import com.example.booking_app.databinding.FragmentHomeBinding
import com.example.booking_app.model.BookRoom
import com.example.booking_app.model.State
import com.example.booking_app.ui.adapter.BookingRoomAdapter
import com.example.booking_app.utils.NetworkUtils
import com.example.booking_app.utils.hide
import com.example.booking_app.utils.show

import com.wizzpass.mybookrooms.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mAdapter = BookingRoomAdapter(this::onItemClicked)


    override fun onStart() {
        super.onStart()
        observeBooks()
        handleNetworkChanges()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initView() {
        binding.run {
            booksRecyclerView.adapter = mAdapter
            swipeRefreshLayout.setOnRefreshListener {
                getBooks()
            }
        }

        // If Current State isn't `Success` then reload posts.
        homeViewModel.booksLiveData.value?.let { currentState ->
            if (!currentState.isSuccessful()) {
                getBooks()
            }
        }
    }

    private fun observeBooks() {
        homeViewModel.booksLiveData.observe(this) { state ->
            when (state) {
                is State.Loading -> {}//showLoading(true)
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        mAdapter.submitList(state.data.toMutableList())
                        showLoading(false)
                    }
                }
                is State.Error -> {
                   showToast(state.message)
                   showLoading(false)
                }
            }
        }
    }

    private fun getBooks() = homeViewModel.getBooks()

    private fun showLoading(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    /**
     * Observe network changes i.e. Internet Connectivity
     */
    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(requireActivity()).observe(this) { isConnected ->
            if (!isConnected) {
                binding.textViewNetworkStatus.text =
                    getString(R.string.text_no_connectivity)
                binding.networkStatusLayout.apply {
                    show()
                    setBackgroundColor(getColorRes(R.color.colorStatusNotConnected))
                }
            } else {
                if (homeViewModel.booksLiveData.value is State.Error || mAdapter.itemCount == 0) {
                    getBooks()
                }
                binding.textViewNetworkStatus.text = getString(R.string.text_connectivity)
                binding.networkStatusLayout.apply {
                    setBackgroundColor(getColorRes(R.color.colorStatusConnected))

                    animate()
                        .alpha(1f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        }
    }

    private fun onItemClicked(room: BookRoom, imageView: ImageView) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            requireActivity(),
            imageView,
            imageView.transitionName
        )




        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToRoomDetailFragment(room))


       // showToast(book.name)


        val postId = room.name ?: run {
            showToast("Unable to launch details")
            return
        }
//        val intent = PostDetailsActivity.getStartIntent(this, postId)
//        startActivity(intent, options.toBundle())
    }

    companion object {
        const val ANIMATION_DURATION = 1000L
    }

}