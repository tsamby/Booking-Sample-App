package com.example.booking_app.ui.roomdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.example.booking_app.R
import com.example.booking_app.databinding.RoomDetailFragmentBinding
import com.shreyaspatil.MaterialDialog.MaterialDialog
import com.wizzpass.mybookrooms.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RoomDetailFragment : Fragment() {




    private val viewModel: RoomDetailViewModel by viewModels()
    private var _binding : RoomDetailFragmentBinding? = null
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RoomDetailFragmentBinding.inflate(inflater, container, false)



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = RoomDetailFragmentArgs.fromBundle(requireArguments())
        //binding.roomDetail.text = args.roomDetail.name
        binding.roomName.text = args.roomDetail.name
        binding.numOfSpots.text = "Number of spots remaining : " + args.roomDetail.spots.toString()
        binding.imageView.load(args.roomDetail.thumbnail)

        binding.buttonConfirm.setOnClickListener{

            MaterialDialog.Builder(requireActivity())
                .setTitle(getString(R.string.confirm_dialog_title))
                .setMessage(getString(R.string.confirm_dialog_message))
                .setPositiveButton(getString(R.string.option_yes)) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    showToast("Yoh have successfully booked "+ args.roomDetail.name)

                }
                .setNegativeButton(getString(R.string.option_no)) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                .build()
                .show()

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}